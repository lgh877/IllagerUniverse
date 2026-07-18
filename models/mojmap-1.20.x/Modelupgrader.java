// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelupgrader<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "upgrader"), "main");
	private final ModelPart body;
	private final ModelPart trueHead;
	private final ModelPart head;
	private final ModelPart mouth;
	private final ModelPart glass;
	private final ModelPart nose2;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart right_arm;
	private final ModelPart hammer;
	private final ModelPart left_arm;
	private final ModelPart wrench;
	private final ModelPart arms;
	private final ModelPart arms_rotation;
	private final ModelPart arms_flipped;
	private final ModelPart none;

	public Modelupgrader(ModelPart root) {
		this.body = root.getChild("body");
		this.trueHead = this.body.getChild("trueHead");
		this.head = this.trueHead.getChild("head");
		this.mouth = this.head.getChild("mouth");
		this.glass = this.head.getChild("glass");
		this.nose2 = this.head.getChild("nose2");
		this.leg0 = this.body.getChild("leg0");
		this.leg1 = this.body.getChild("leg1");
		this.right_arm = this.body.getChild("right_arm");
		this.hammer = this.right_arm.getChild("hammer");
		this.left_arm = this.body.getChild("left_arm");
		this.wrench = this.left_arm.getChild("wrench");
		this.arms = this.body.getChild("arms");
		this.arms_rotation = this.arms.getChild("arms_rotation");
		this.arms_flipped = this.arms_rotation.getChild("arms_flipped");
		this.none = root.getChild("none");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(16, 20)
						.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 38)
						.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 14.0F, 6.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition trueHead = body.addOrReplaceChild("trueHead", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = trueHead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F,
				-10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F,
				0.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -3.1F));

		PartDefinition glass = head.addOrReplaceChild("glass",
				CubeListBuilder.create().texOffs(32, 8)
						.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.25F)).texOffs(32, 9)
						.addBox(1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 9)
						.addBox(-3.0F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 11)
						.addBox(-3.0F, -3.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 11)
						.addBox(1.0F, -3.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 6)
						.addBox(3.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 6)
						.addBox(-4.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 6)
						.addBox(-1.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 6)
						.addBox(0.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 13)
						.addBox(1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 13)
						.addBox(-3.0F, -2.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition nose2 = head.addOrReplaceChild("nose2", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F,
				-1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition leg0 = body.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F,
				-2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition leg1 = body.addOrReplaceChild("leg1",
				CubeListBuilder.create().texOffs(0, 22).mirror()
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(
				-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition hammer = right_arm.addOrReplaceChild("hammer",
				CubeListBuilder.create().texOffs(56, 56)
						.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 58)
						.addBox(-3.0F, -7.0F, -2.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 58)
						.addBox(-3.0F, -7.0F, 0.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 8.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(40, 46).mirror()
						.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition wrench = left_arm.addOrReplaceChild("wrench",
				CubeListBuilder.create().texOffs(56, 49)
						.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 47)
						.addBox(-2.0F, -4.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 46)
						.addBox(1.0F, -7.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 46)
						.addBox(-2.0F, -7.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 8.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(),
				PartPose.offset(0.0F, 3.5F, 0.3F));

		PartDefinition arms_rotation = arms.addOrReplaceChild("arms_rotation",
				CubeListBuilder.create().texOffs(44, 22)
						.addBox(-8.0F, 0.0F, -2.05F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(40, 38)
						.addBox(-4.0F, 4.0F, -2.05F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.05F, -0.7854F, 0.0F, 0.0F));

		PartDefinition arms_flipped = arms_rotation.addOrReplaceChild("arms_flipped",
				CubeListBuilder.create().texOffs(44, 22).mirror()
						.addBox(4.0F, -24.0F, -2.05F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition none = partdefinition.addOrReplaceChild("none", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		none.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
	}
}